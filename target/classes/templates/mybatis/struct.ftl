package ${package}.struct;

import ${package}.model.${pascalName};
import ${package}.dto.${pascalName}DTO;
import ${package}.vo.${pascalName}VO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

/**
  * ${tableComment}转换类
  */
@Mapper
public interface ${pascalName}Struct {

    ${pascalName}Struct INSTANCE = Mappers.getMapper(${pascalName}Struct.class);

    /**
    * ${pascalName} 转 ${pascalName}VO
    *
    * @param record
    * @return
    */
    ${pascalName}VO modelToVO(${pascalName} record);

    /**
    * List<${pascalName}> 转 List<${pascalName}VO>
    *
    * @param records
    * @return
    */
    List<${pascalName}VO> modelToVO(List<${pascalName}> records);

    /**
    * ${pascalName}VO 转 ${pascalName}
    *
    * @param record
    * @return
    */
    ${pascalName} voToModel(${pascalName}VO record);

    /**
    * List<${pascalName}VO> 转 List<${pascalName}>
    *
    * @param records
    * @return
    */
    List<${pascalName}> voToModel(List<${pascalName}VO> records);

    /**
    * ${pascalName} 转 ${pascalName}DTO
    *
    * @param record
    * @return
    */
    ${pascalName}DTO modelToDTO(${pascalName} record);

    /**
    * List<${pascalName}> 转 List<${pascalName}DTO>
    *
    * @param records
    * @return
    */
    List<${pascalName}DTO> modelToDTO(List<${pascalName}> records);

    /**
    * ${pascalName}DTO 转 ${pascalName}
    *
    * @param record
    * @return
    */
    ${pascalName} dtoToModel(${pascalName}DTO record);

    /**
    * List<${pascalName}DTO> 转 List<${pascalName}>
    *
    * @param records
    * @return
    */
    List<${pascalName}> dtoToModel(List<${pascalName}DTO> records);
}