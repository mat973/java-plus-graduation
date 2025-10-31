package ru.practicum;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.grpc.stats.event.EventAnalyzerGrpc;
import stats.messages.analyzer.AnalyzerMessages;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


@Service
public class RecommendationsClient {

    private final EventAnalyzerGrpc.EventAnalyzerBlockingStub client;

    public RecommendationsClient(@GrpcClient("analyzer") EventAnalyzerGrpc.EventAnalyzerBlockingStub client) {
        this.client = client;
    }

    public Stream<AnalyzerMessages.RecommendedEventProto> getRecommendationsForUser(long userId, int maxResults) {
        AnalyzerMessages.UserPredictionsRequestProto request = AnalyzerMessages.UserPredictionsRequestProto.newBuilder()
                .setUserId(userId)
                .setMaxResults(maxResults)
                .build();

        Iterator<AnalyzerMessages.RecommendedEventProto> iterator = client.getRecommendationsForUser(request);

        return asStream(iterator);
    }

    public Stream<AnalyzerMessages.RecommendedEventProto> getSimilarEvents(long eventId, long userId, int maxResults) {
        AnalyzerMessages.SimilarEventsRequestProto request = AnalyzerMessages.SimilarEventsRequestProto.newBuilder()
                .setEventId(eventId)
                .setUserId(userId)
                .setMaxResults(maxResults)
                .build();

        Iterator<AnalyzerMessages.RecommendedEventProto> iterator = client.getSimilarEvents(request);

        return asStream(iterator);
    }

    public Stream<AnalyzerMessages.RecommendedEventProto> getInteractionsCount(List<Long> eventIds) {
        AnalyzerMessages.InteractionsCountRequestProto request = AnalyzerMessages.InteractionsCountRequestProto.newBuilder()
                .addAllEventId(eventIds)
                .build();

        Iterator<AnalyzerMessages.RecommendedEventProto> iterator = client.getInteractionsCount(request);

        return asStream(iterator);
    }

    private Stream<AnalyzerMessages.RecommendedEventProto> asStream(Iterator<AnalyzerMessages.RecommendedEventProto> iterator) {
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED),
                false
        );
    }
}
