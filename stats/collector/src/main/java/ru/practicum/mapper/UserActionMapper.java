package ru.practicum.mapper;

import com.google.protobuf.Timestamp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.practicum.ewm.stats.avro.UserActionAvro;
import stats.messages.collector.UserAction;


import java.time.Instant;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static stats.messages.collector.UserAction.ActionTypeProto.*;

@Mapper(componentModel = SPRING)
public interface UserActionMapper {

    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "eventId", target = "eventId")
    @Mapping(source = "actionType", target = "actionType", qualifiedByName = "protoToAvroActionType")
    @Mapping(source = "timestamp", target = "timestamp", qualifiedByName = "protoTimestampToMillis")
    UserActionAvro toAvro(UserAction.UserActionProto proto);

    @Named("protoToAvroActionType")
    default ru.practicum.ewm.stats.avro.ActionType protoToAvroActionType(
            UserAction.ActionTypeProto actionType) {
        if (actionType == null) return null;

        return switch (actionType) {
            case ACTION_VIEW -> ru.practicum.ewm.stats.avro.ActionType.VIEW;
            case ACTION_REGISTER -> ru.practicum.ewm.stats.avro.ActionType.REGISTER;
            case ACTION_LIKE -> ru.practicum.ewm.stats.avro.ActionType.LIKE;
            default -> throw new IllegalArgumentException("Unknown proto action type: " + actionType);
        };
    }

    @Named("protoTimestampToMillis")
    default long protoTimestampToMillis(com.google.protobuf.Timestamp timestamp) {
        if (timestamp == null) return 0L;
        return timestamp.getSeconds() * 1000 + timestamp.getNanos() / 1_000_000;
    }
}