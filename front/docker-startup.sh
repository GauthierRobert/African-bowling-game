#!/bin/sh

sed -i "s#window.serverUrl = undefined#window.serverUrl = '$SERVER_URL'#g" /usr/share/nginx/html/index.html;

nginx -g 'daemon off;';
