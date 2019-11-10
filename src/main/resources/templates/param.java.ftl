package finance.temp;


import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
* <p>
    * ${table.comment}
    * </p>
*
* @author ${author}
* @since ${date}
*/
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ${table.paramName} implements Serializable {


private static final long serialVersionUID = 1L;


<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
<#-- 如果是主键 -->
<#if field.keyFlag>
    <#if field.comment!?length gt 0>
        /**
        * ${field.comment}
        */
    </#if>
    Set<Long> ${field.propertyName}s;
    <#else>
        <#if field.propertyType ?? && field.propertyType == "LocalDate" >
            /**
            *
            *${field.comment}-开始时间
            */
            ${field.propertyType} start${field.propertyName?cap_first};
            /**
            *
            *${field.comment}-结束时间
            */
            ${field.propertyType} end${field.propertyName?cap_first};
        <#elseif field.propertyType ?? && field.propertyType == "String">
            /**
            *
            *${field.comment}-模糊查询
            */
            String ${field.propertyName}Query;
        <#else>
            /**
            *
            *${field.comment}-集合查询
            */
            Set<${field.propertyType}> ${field.propertyName}s;
        </#if>

    </#if>
    </#list>
    <#------------  END 字段循环遍历  ---------->


    }
