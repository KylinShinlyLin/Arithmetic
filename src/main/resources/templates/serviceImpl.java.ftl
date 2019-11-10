package finance.temp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.apache.commons.lang.StringUtils;
import java.util.Set;
import java.util.Objects;

/**
* <p>
    * ${table.comment!} 服务实现类
    * </p>
*
* @author ${author}
* @since ${date}
*/
@Service
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {


private QueryWrapper<${entity}> getQueryWrapperByParamDTO(${table.paramName} paramDTO){
QueryWrapper<${entity}> queryWrapper = new QueryWrapper<>();
<#list table.fields as field>
    <#if field.propertyType ?? && field.propertyType == "LocalDate" >
        if (Objects.nonNull(paramDTO.getStart${field.propertyName?cap_first}())) {
        queryWrapper.ge(${entity}.${field.name?upper_case}, paramDTO.getStart${field.propertyName?cap_first}());
        }
        if (Objects.nonNull(paramDTO.getEnd${field.propertyName?cap_first}())) {
        queryWrapper.lt(${entity}.${field.name?upper_case}, paramDTO.getEnd${field.propertyName?cap_first}());
        }
    <#elseif field.propertyType ?? && field.propertyType == "String">
        if (StringUtils.isNotBlank(paramDTO.get${field.propertyName?cap_first}Query())) {
        queryWrapper.likeRight(${entity}.${field.name?upper_case}, paramDTO.get${field.propertyName?cap_first}Query());
        }
    <#else>
        if (CollectionUtils.isNotEmpty(paramDTO.get${field.propertyName?cap_first}s())) {
        queryWrapper.in(${entity}.${field.name?upper_case}, paramDTO.get${field.propertyName?cap_first}s());
        }
    </#if>

</#list>


}
}