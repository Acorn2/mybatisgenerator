package ${package}.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msdn.generator.utils.BeanUtils;
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
public class ${pascalName}ServiceImpl extends ServiceImpl<${pascalName}Mapper, ${pascalName}> implements ${pascalName}Service {

    @Autowired
    private ${pascalName}Mapper ${camelName}Mapper;

    @Override
    public IPage<${pascalName}VO> queryPage(${pascalName}QueryPageDTO dto) {
        IPage<${pascalName}VO> ${camelName}Page = this.lambdaQuery().page(dto)
            .convert(${camelName} -> BeanUtils.copyProperties(${camelName}, ${pascalName}VO.class));
        return ${camelName}Page;
    }

    @Override
    public List<${pascalName}VO> queryList(${pascalName}QueryPageDTO dto) {
        List<${pascalName}> ${camelName}List = this.lambdaQuery().list();
        return BeanUtils.copyProperties(${camelName}List, ${pascalName}VO.class);
<#--        return ${pascalName}Struct.INSTANCE.modelToVO(${camelName}List, ${pascalName}VO.class);-->
    }

    @Override
    public ${pascalName}VO get(String id) {
        return BeanUtils.copyProperties(${camelName}Mapper.selectById(id), ${pascalName}VO.class);
<#--        return ${pascalName}Struct.INSTANCE.modelToVO(${camelName}Mapper.selectById(id), ${pascalName}VO.class);-->
    }

    @Override
    public void add(${pascalName}DTO dto) {
        this.save(BeanUtils.copyProperties(dto,${pascalName}.class));
<#--        this.save(${pascalName}Struct.INSTANCE.dtoToModel(dto));-->
    }

    @Override
    public void edit(${pascalName}DTO dto) {
        this.updateById(BeanUtils.copyProperties(dto,${pascalName}.class));
<#--        this.updateById(${pascalName}Struct.INSTANCE.dtoToModel(dto));-->
    }

    @Override
    public void delete(String id) {
        this.removeById(id);
    }
}
