FROM node:20.9.0-alpine

WORKDIR /app

COPY package*.json ./

RUN npm install --verbose

COPY . .

EXPOSE 4200

CMD ["npx", "ng", "serve", "--host", "0.0.0.0"]
