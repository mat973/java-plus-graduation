package ru.practicum;

import com.google.protobuf.Timestamp;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.grpc.stats.action.UserActionControllerGrpc;
import stats.messages.collector.UserAction;

import java.time.Instant;

@Service
public class UserActionClient {

    private final UserActionControllerGrpc.UserActionControllerBlockingStub userActionStub;

    public UserActionClient(@GrpcClient("COLLECTOR") UserActionControllerGrpc.UserActionControllerBlockingStub client) {
        this.userActionStub = client;
    }

    public void collectUserAction(Long eventId, Long userId, UserAction.ActionTypeProto type, Instant instant) {
        UserAction.UserActionProto request = UserAction.UserActionProto.newBuilder()
                .setEventId(eventId)
                .setUserId(userId)
                .setActionType(type)
                .setTimestamp(mapToTimestamp(instant))
                .build();

        userActionStub.collectUserAction(request);
    }

    private Timestamp mapToTimestamp(Instant instant) {
        return Timestamp.newBuilder()
                .setSeconds(instant.getEpochSecond())
                .setNanos(instant.getNano())
                .build();
    }
}
