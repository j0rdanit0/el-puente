import boto3
import logging
from urllib.request import urlopen
import os


def lambda_handler(event, context):
    ipPermissions = {
        'IpProtocol': os.getenv('IP_PROTOCOL', 'tcp'),
        'FromPort': os.getenv('FROM_PORT', 80),
        'ToPort': os.getenv('TO_PORT', 80),
        'IpRanges': [],
        'Ipv6Ranges': []
    }

    CLOUDFLARE_IPSv4_URL = os.getenv('CLOUDFLARE_IPSv4_URL', 'https://www.cloudflare.com/ips-v4')
    for ipv4 in urlopen(CLOUDFLARE_IPSv4_URL):
        ipPermissions['IpRanges'].append({
            'CidrIp': ipv4,
            'Description': 'Cloudflare IPv4 - updated by Lambda'
        })
        logging.debug('Got IPv4: ' + ipv4)

    CLOUDFLARE_IPSv6_URL = os.getenv('CLOUDFLARE_IPSv6_URL', 'https://www.cloudflare.com/ips-v6')
    for ipv6 in urlopen(CLOUDFLARE_IPSv6_URL):
        ipPermissions['Ipv6Ranges'].append({
            'CidrIpv6': ipv6,
            'Description': 'Cloudflare IPv6 - updated by Lambda'
        })
        logging.debug('Got IPv6: ' + ipv6)

    SECURITY_GROUP_ID = os.getenv('SECURITY_GROUP_ID')

    result = 'Unable to get IPs from Cloudflare.'
    if len(ipPermissions['IpRanges']) > 0 and len(ipPermissions['Ipv6Ranges']) > 0:
        ec2 = boto3.client('ec2')

        logging.info('Getting all current security group rules and removing them...')
        old_ip_permissions = ec2.describe_security_groups(output='json', GroupIds=[SECURITY_GROUP_ID])['SecurityGroups'][0]['IpPermissions']
        ec2.revoke_security_group_ingress(GroupId=SECURITY_GROUP_ID, IpPermissions=[old_ip_permissions])

        logging.info('Adding new security group rules...')
        result = ec2.authorize_security_group_ingress(GroupId=SECURITY_GROUP_ID, IpPermissions=[ipPermissions])

    return result
