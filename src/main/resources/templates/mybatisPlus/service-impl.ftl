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
        return BeanUtils.copyProperties(this.getById(id), ${pascalName}VO.class);
    }

    @Override
    public Boolean add(${pascalName}DTO dto) {
        return this.save(BeanUtils.copyProperties(dto,${pascalName}.class));
<#--        this.save(${pascalName}Struct.INSTANCE.dtoToModel(dto));-->
    }

    @Override
    public Boolean edit(${pascalName}DTO dto) {
        return this.updateById(BeanUtils.copyProperties(dto,${pascalName}.class));
<#--        this.updateById(${pascalName}Struct.INSTANCE.dtoToModel(dto));-->
    }

    @Override
    public Boolean delete(String id) {
        return this.removeById(id);
    }
}
