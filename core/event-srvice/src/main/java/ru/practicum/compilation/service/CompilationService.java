package ru.practicum.compilation.service;



import ru.practicum.dto.event.compilationDto.CompilationDto;
import ru.practicum.dto.event.compilationDto.CompilationRequestDto;
import ru.practicum.dto.event.compilationDto.NewCompilationDto;
import ru.practicum.dto.event.compilationDto.UpdateCompilationRequest;

import java.util.List;

public interface CompilationService {
    List<CompilationDto> getAll(CompilationRequestDto requestDto);

    CompilationDto getById(Long compId);

    CompilationDto create(NewCompilationDto dto);

    void delete(Long compId);

    CompilationDto update(Long compId, UpdateCompilationRequest request);
}
