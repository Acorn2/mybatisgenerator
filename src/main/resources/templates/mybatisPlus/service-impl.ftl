package ${package}.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
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
public class ${pascalName}ServiceImpl extends ServiceImpl<${pascalName}Mapper, ${pascalName}> implements ${pascalName}Service {

    @Override
    public IPage<${pascalName}VO> queryPage(${pascalName}QueryPageDTO dto) {
        IPage<${pascalName}> ${camelName}Page = this.page(PageUtils.getPage(dto.getPageSortInfo()), Wrappers.lambdaQuery());
        return ${camelName}Page;
    }

    @Override
    public List<${pascalName}VO> queryList(${pascalName}QueryPageDTO dto) {
        List<${pascalName}> ${camelName}List = this.list(Wrappers.lambdaQuery());
        return ${pascalName}Struct.INSTANCE.modelToVO(${camelName}List, ${pascalName}VO.class);
    }

    @Override
    public ${pascalName}VO get(String id) {
        return ${pascalName}Struct.INSTANCE.modelToVO(${camelName}Mapper.selectById(id), ${pascalName}VO.class);
    }

    @Override
    public void add(${pascalName}DTO dto) {
        this.save(${pascalName}Struct.INSTANCE.dtoToModel(dto));
    }

    @Override
    public void edit(${pascalName}DTO dto) {
        this.updateById(${pascalName}Struct.INSTANCE.dtoToModel(dto));
    }

    @Override
    public void delete(String id) {
        this.deleteById(id);
    }
}
