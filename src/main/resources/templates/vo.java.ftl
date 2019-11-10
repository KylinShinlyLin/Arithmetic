package finance.temp;


import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import java.io.Serializable;
import java.time.LocalDate;

/**
* <p>
    * ${table.comment}
    * </p>
*
* @author ${author}
* @since ${date}
*/
@ApiModel(description = "${table.comment}")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ${table.voName} implements Serializable {


private static final long serialVersionUID = 1L;


<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if field.keyFlag>
        <#assign keyPropertyName="${field.propertyName}"/>
    </#if>

    <#if field.comment!?length gt 0>
        /**
        * ${field.comment}
        */
    </#if>
    @ApiParam("${field.comment}")
    ${field.propertyType} ${field.propertyName};
</#list>
<#------------  END 字段循环遍历  ---------->

/**
*
* ${table.dtoName} 实体转DTO
*
*/
public static ${table.voName} dto2Vo(${entity}DTO dto){
return ${table.voName}.builder()
<#list table.fields as field>
    .${field.propertyName}(dto.get${field.propertyName?cap_first}())
</#list>
.build();
}

}
