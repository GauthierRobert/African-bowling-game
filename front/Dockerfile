### STAGE 1: Build ###

# We label our stage as ‘builder’
FROM node:16-alpine as builder

COPY package.json package-lock.json ./

## Storing node modules on a separate layer will prevent unnecessary npm installs at each build

RUN npm ci && mkdir /ng-app && mv ./node_modules ./ng-app

WORKDIR /ng-app

COPY . .

## Build the angular app in production mode and store the artifacts in dist folder

RUN npm run ng build -- --configuration=production --output-path=dist


### STAGE 2: Setup ###

FROM nginx:1.21.6-alpine

EXPOSE 80

ENV SERVER_URL 'http://localhost:8080'

## From ‘builder’ stage copy over the artifacts in dist folder to default nginx public folder
COPY --from=builder /ng-app/dist /usr/share/nginx/html

COPY docker-startup.sh /docker-startup.sh
RUN chmod +x /docker-startup.sh

ENTRYPOINT ["/docker-startup.sh"]
