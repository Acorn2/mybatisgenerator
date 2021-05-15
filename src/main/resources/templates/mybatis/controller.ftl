package ${package}.controller;

import com.github.pagehelper.Page;
import com.msdn.generator.common.dto.Result;
import com.msdn.generator.common.dto.PageResult;
import ${package}.dto.${pascalName}DTO;
import ${package}.dto.${pascalName}QueryPageDTO;
import ${package}.service.${pascalName}Service;
import ${package}.vo.${pascalName}VO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = {"${tableComment}"})
@RequestMapping("/${camelName}")
public class ${pascalName}Controller {

    @Autowired
    private ${pascalName}Service ${camelName}Service;

    @PostMapping(value = "/queryPage")
    @ApiOperation("获取${tableComment}分页列表")
    public Result<PageResult<${pascalName}VO>> queryPage(@RequestBody ${pascalName}QueryPageDTO dto) {
        Page<${pascalName}VO> ${camelName}VOPage = ${camelName}Service.queryPage(dto);
        return Result.ok(PageResult.ok(${camelName}VOPage));
    }

    @PostMapping(value = "/queryList")
    @ApiOperation("获取${tableComment}列表")
    public Result<List<${pascalName}VO>> queryList(@RequestBody ${pascalName}QueryPageDTO dto) {
        List<${pascalName}VO> ${camelName}VOList = ${camelName}Service.queryList(dto);
        return Result.ok(${camelName}VOList);
    }

    @GetMapping(value = "/get")
    @ApiOperation("获取${tableComment}详情")
    public Result<${pascalName}VO> get(@RequestParam String id) {
        ${pascalName}VO ${camelName}VO = ${camelName}Service.get(id);
        return Result.ok(${camelName}VO);
    }

    @PostMapping(value = "/add")
    @ApiOperation("新增${tableComment}")
    public Result add(@RequestBody ${pascalName}DTO dto) {
        int count = ${camelName}Service.add(dto);
        if (count < 1) {
            return Result.failed();
        }
        return Result.ok();
    }

    @PostMapping(value = "/edit")
    @ApiOperation("编辑${tableComment}")
    public Result edit(@RequestBody ${pascalName}DTO dto) {
        int count = ${camelName}Service.edit(dto);
        if (count < 1) {
            return Result.failed();
        }
        return Result.ok();
    }

    @GetMapping(value = "/delete")
    @ApiOperation("删除${tableComment}")
    public Result delete(@RequestParam String id) {
        int count = ${camelName}Service.delete(id);
        if (count < 1) {
            return Result.failed();
        }
        return Result.ok();
    }
}
