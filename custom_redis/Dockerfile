FROM redis:4.0.11

COPY ./scripts/health.groovy /scripts/
COPY ./start.sh /start.sh

RUN chmod +x /start.sh

EXPOSE 6379
VOLUME /scripts

ENTRYPOINT ["/start.sh"]
CMD []
