ARG NODE_VERSION=18.16.1

FROM node:${NODE_VERSION}-alpine as BUILDER


WORKDIR /app

COPY package.json .
COPY yarn.lock .

RUN yarn install

COPY . .

RUN yarn compile

FROM node:${NODE_VERSION}-alpine as RUNNER
WORKDIR /app

COPY package.json .
COPY yarn.lock .

RUN yarn install

COPY --from=BUILDER /app/build/src src

# Run the application as a non-root user.
USER node


# Expose the port that the application listens on.
EXPOSE 3000

# Run the application.
CMD ["yarn", "deploy"]
