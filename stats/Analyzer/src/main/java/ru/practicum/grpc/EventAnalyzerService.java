package ru.practicum.grpc;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import ru.practicum.ewm.grpc.stats.event.EventAnalyzerGrpc;
import stats.messages.analyzer.*;


@Slf4j
@GrpcService
public class EventAnalyzerService extends EventAnalyzerGrpc.EventAnalyzerImplBase {

    @Override
    public void getRecommendationsForUser(
            AnalyzerMessages.UserPredictionsRequestProto request,
            StreamObserver<AnalyzerMessages.RecommendedEventProto> responseObserver
    ) {
        log.info("Получен gRPC-запрос: GetRecommendationsForUser user_id={} max_results={}",
                request.getUserId(), request.getMaxResults());

        // Пока просто возвращаем пустой результат
        AnalyzerMessages.RecommendedEventProto response = AnalyzerMessages.RecommendedEventProto.newBuilder()
                .setEventId(-1)
                .setScore(0.0)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getSimilarEvents(
            AnalyzerMessages.SimilarEventsRequestProto request,
            StreamObserver<AnalyzerMessages.RecommendedEventProto> responseObserver
    ) {
        log.info("Получен gRPC-запрос: GetSimilarEvents event_id={} user_id={} max_results={}",
                request.getEventId(), request.getUserId(), request.getMaxResults());

        // Возвращаем пустой стрим (заглушка)
        AnalyzerMessages.RecommendedEventProto proto = AnalyzerMessages.RecommendedEventProto.newBuilder()
                .setEventId(-1)
                .setScore(0.0)
                .build();

        responseObserver.onNext(proto);
        responseObserver.onCompleted();
    }

    @Override
    public void getInteractionsCount(
            AnalyzerMessages.InteractionsCountRequestProto request,
            StreamObserver<AnalyzerMessages.RecommendedEventProto> responseObserver
    ) {
        log.info("Получен gRPC-запрос: GetInteractionsCount event_ids={}", request.getEventIdList());

        // Возвращаем пустой стрим (заглушка)
        AnalyzerMessages.RecommendedEventProto proto = AnalyzerMessages.RecommendedEventProto.newBuilder()
                .setEventId(-1)
                .setScore(0.0)
                .build();

        responseObserver.onNext(proto);
        responseObserver.onCompleted();
    }
}

