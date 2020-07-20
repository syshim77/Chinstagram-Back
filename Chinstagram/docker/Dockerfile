FROM centos:7

# Install basic packages
ENV JAVA_TOOL_OPTIONS -Dfile.encoding=UTF8
ENV LANG kr_KO.UTF-8

RUN yum -y update
RUN yum install -y wget telnet nc tar vim unzip net-tools java-11-openjdk-devel

# Chinstagram setup
RUN mkdir -p /chinstagram/operation
VOLUME ["/chinstagram/operation"]
EXPOSE 80 8080
WORKDIR /chinstagram
ADD run.sh /chinstagram/run.sh
RUN chmod 755 /chinstagram/run.sh
ADD war/*.war /chinstagram/

ENTRYPOINT ["/chinstagram/run.sh"]