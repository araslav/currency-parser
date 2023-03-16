package pet.project.currencyparser.dto;

public interface ModelMapper<M, R> {
    M toModel(R requestDto);
}
