package com.sberned.grpc.test.server.service.interceptor;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Используем дополнительные возможности gRPC:
 * перехватываем и логируем вызовы всех сервисов
 */
@Component
public class LogInterceptor implements ServerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(LogInterceptor.class);

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call,
                                                                 Metadata headers,
                                                                 ServerCallHandler<ReqT, RespT> next) {
        log.info("gRPC method called: " + call.getMethodDescriptor().getFullMethodName());
        return next.startCall(call, headers);
    }
}