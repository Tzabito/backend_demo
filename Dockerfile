FROM ubuntu:latest
LABEL authors="migue"

ENTRYPOINT ["top", "-b"]