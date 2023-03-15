package Backend.AdminBackend.mapper;

public interface MapperInterface <T,U>{
    T toModel(U dto);

    U toDto(T entity);
}
