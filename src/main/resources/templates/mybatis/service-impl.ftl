package ${package}.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.msdn.generator.utils.BeanUtils;
import com.msdn.generator.utils.PageUtils;
import ${package}.dto.${pascalName}DTO;
import ${package}.dto.${pascalName}QueryPageDTO;
import ${package}.mapper.${pascalName}Mapper;
import ${package}.model.${pascalName};
import ${package}.service.${pascalName}Service;
import ${package}.vo.${pascalName}VO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ${pascalName}ServiceImpl implements ${pascalName}Service {

    @Autowired
    private ${pascalName}Mapper ${camelName}Mapper;

    @Override
    public Page<${pascalName}VO> queryPage(${pascalName}QueryPageDTO dto) {
        ${pascalName} ${camelName} = BeanUtils.copyProperties(dto, ${pascalName}.class);
        PageHelper.startPage(dto.getPageSortInfo().getPageNum(), dto.getPageSortInfo().getPageSize(), dto.getPageSortInfo().parseSort());
        Page<${pascalName}> ${camelName}Page = (Page<${pascalName}>) ${camelName}Mapper.select(${camelName});
        return PageUtils.convert(${camelName}Page, ${pascalName}VO.class);
    }

    @Override
    public List<${pascalName}VO> queryList(${pascalName}QueryPageDTO dto) {
        ${pascalName} ${camelName} = BeanUtils.copyProperties(dto, ${pascalName}.class);
        return BeanUtils.copyProperties(${camelName}Mapper.select(${camelName}), ${pascalName}VO.class);
<#--        return ${pascalName}Struct.INSTANCE.modelToVO(${camelName}Mapper.select(${camelName}), ${pascalName}VO.class);-->
    }

    @Override
    public ${pascalName}VO get(String id) {
        return BeanUtils.copyProperties(${camelName}Mapper.selectByPrimaryKey(id), ${pascalName}VO.class);
<#--        return ${pascalName}Struct.INSTANCE.modelToVO(${camelName}Mapper.selectByPrimaryKey(id), ${pascalName}VO.class);-->
    }

    @Override
    public int add(${pascalName}DTO dto) {
        return ${camelName}Mapper.insertSelective(BeanUtils.copyProperties(dto, ${pascalName}.class));
<#--        ${camelName}Mapper.insert(${pascalName}Struct.INSTANCE.dtoToModel(dto));-->
    }

    @Override
    public int edit(${pascalName}DTO dto) {
        return ${camelName}Mapper.updateByPrimaryKeySelective(BeanUtils.copyProperties(dto, ${pascalName}.class));
<#--        ${camelName}Mapper.updateByPrimaryKeySelective(${pascalName}Struct.INSTANCE.dtoToModel(dto));-->
    }

    @Override
    public int delete(String id) {
        return ${camelName}Mapper.deleteByPrimaryKey(id);
    }
}
