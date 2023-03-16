package pet.project.currencyparser.dto;

public interface ResponseMapper<M, R> {
    R toResponseDto(M model);
}
