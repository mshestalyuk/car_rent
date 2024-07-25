# Frontend Dockerfile
FROM node:20.9.0-alpine AS build

WORKDIR /app

COPY package*.json ./

RUN npm ci

RUN npm install -g @angular/cli

COPY . .

RUN ng build --configuration=production

FROM nginx:latest

COPY ./nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=build /app/dist/demo/browser /usr/share/nginx/html

EXPOSE 4200

CMD ["nginx", "-g", "daemon off;"]
