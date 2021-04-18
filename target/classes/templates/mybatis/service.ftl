package ${package}.service;

import com.github.pagehelper.Page;
import ${package}.dto.${pascalName}DTO;
import ${package}.dto.${pascalName}QueryListDTO;
import ${package}.dto.${pascalName}QueryPageDTO;
import ${package}.vo.${pascalName}VO;

import java.util.List;

public interface ${pascalName}Service {

	// 获取${tableComment}分页列表
	Page<${pascalName}VO> queryPage(${pascalName}QueryPageDTO dto);

	// 获取${tableComment}列表
	List<${pascalName}VO> queryList(${pascalName}QueryPageDTO dto);

	// 获取${tableComment}详情
	${pascalName}VO get(String id);

	// 新增${tableComment}
	void add(${pascalName}DTO dto);

	// 编辑${tableComment}
	void edit(${pascalName}DTO dto);

	// 删除${tableComment}
	void delete(String id);
}
