/*
 *
 *  Copyright 2016 Netflix, Inc.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 *
 */
package com.netflix.hollow.api.codegen.delegate;

import static com.netflix.hollow.api.codegen.HollowCodeGenerationUtils.delegateInterfaceName;
import static com.netflix.hollow.api.codegen.HollowCodeGenerationUtils.delegateLookupImplName;
import static com.netflix.hollow.api.codegen.HollowCodeGenerationUtils.substituteInvalidChars;
import static com.netflix.hollow.api.codegen.HollowCodeGenerationUtils.typeAPIClassname;
import static com.netflix.hollow.api.codegen.HollowCodeGenerationUtils.uppercase;

import com.netflix.hollow.api.custom.HollowAPI;

import com.netflix.hollow.core.schema.HollowObjectSchema;
import com.netflix.hollow.api.codegen.HollowAPIGenerator;
import com.netflix.hollow.api.codegen.HollowJavaFileGenerator;
import com.netflix.hollow.api.objects.delegate.HollowObjectAbstractDelegate;
import com.netflix.hollow.core.read.dataaccess.HollowObjectTypeDataAccess;

/**
 * This class contains template logic for generating a {@link HollowAPI} implementation.  Not intended for external consumption.
 * 
 * @see HollowAPIGenerator
 * 
 * @author dkoszewnik
 *
 */
public class HollowObjectDelegateLookupImplGenerator implements HollowJavaFileGenerator {

    private final String packageName;
    private final HollowObjectSchema schema;
    private final String className;

    public HollowObjectDelegateLookupImplGenerator(String packageName, HollowObjectSchema schema) {
        this.packageName = packageName;
        this.schema = schema;
        this.className = delegateLookupImplName(schema.getName());
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public String generate() {

        StringBuilder builder = new StringBuilder();

        builder.append("package ").append(packageName).append(";\n\n");

        builder.append("import ").append(HollowObjectAbstractDelegate.class.getName()).append(";\n");
        builder.append("import ").append(HollowObjectTypeDataAccess.class.getName()).append(";\n");
        builder.append("import ").append(HollowObjectSchema.class.getName()).append(";\n");

        builder.append("\n@SuppressWarnings(\"all\")\n");
        builder.append("public class ").append(className).append(" extends HollowObjectAbstractDelegate implements ").append(delegateInterfaceName(schema.getName())).append(" {\n\n");

        builder.append("    private final ").append(typeAPIClassname(schema.getName())).append(" typeAPI;\n\n");

        builder.append("    public ").append(className).append("(").append(typeAPIClassname(schema.getName())).append(" typeAPI) {\n");
        builder.append("        this.typeAPI = typeAPI;\n");
        builder.append("    }\n\n");

        for(int i=0;i<schema.numFields();i++) {
            String methodFieldName = substituteInvalidChars(uppercase(schema.getFieldName(i)));

            switch(schema.getFieldType(i)) {
            case BOOLEAN:
                builder.append("    public boolean get").append(methodFieldName).append("(int ordinal) {\n");
                builder.append("        return typeAPI.get").append(methodFieldName).append("(ordinal);\n");
                builder.append("    }\n\n");
                builder.append("    public Boolean get").append(methodFieldName).append("Boxed(int ordinal) {\n");
                builder.append("        return typeAPI.get").append(methodFieldName).append("Boxed(ordinal);\n");
                builder.append("    }\n\n");
                break;
            case BYTES:
                builder.append("    public byte[] get").append(methodFieldName).append("(int ordinal) {\n");
                builder.append("        return typeAPI.get").append(methodFieldName).append("(ordinal);\n");
                builder.append("    }\n\n");
                break;
            case DOUBLE:
                builder.append("    public double get").append(methodFieldName).append("(int ordinal) {\n");
                builder.append("        return typeAPI.get").append(methodFieldName).append("(ordinal);\n");
                builder.append("    }\n\n");
                builder.append("    public Double get").append(methodFieldName).append("Boxed(int ordinal) {\n");
                builder.append("        return typeAPI.get").append(methodFieldName).append("Boxed(ordinal);\n");
                builder.append("    }\n\n");
                break;
            case FLOAT:
                builder.append("    public float get").append(methodFieldName).append("(int ordinal) {\n");
                builder.append("        return typeAPI.get").append(methodFieldName).append("(ordinal);\n");
                builder.append("    }\n\n");
                builder.append("    public Float get").append(methodFieldName).append("Boxed(int ordinal) {\n");
                builder.append("        return typeAPI.get").append(methodFieldName).append("Boxed(ordinal);\n");
                builder.append("    }\n\n");
                break;
            case INT:
                builder.append("    public int get").append(methodFieldName).append("(int ordinal) {\n");
                builder.append("        return typeAPI.get").append(methodFieldName).append("(ordinal);\n");
                builder.append("    }\n\n");
                builder.append("    public Integer get").append(methodFieldName).append("Boxed(int ordinal) {\n");
                builder.append("        return typeAPI.get").append(methodFieldName).append("Boxed(ordinal);\n");
                builder.append("    }\n\n");
                break;
            case LONG:
                builder.append("    public long get").append(methodFieldName).append("(int ordinal) {\n");
                builder.append("        return typeAPI.get").append(methodFieldName).append("(ordinal);\n");
                builder.append("    }\n\n");
                builder.append("    public Long get").append(methodFieldName).append("Boxed(int ordinal) {\n");
                builder.append("        return typeAPI.get").append(methodFieldName).append("Boxed(ordinal);\n");
                builder.append("    }\n\n");
                break;
            case REFERENCE:
                builder.append("    public int get").append(methodFieldName).append("Ordinal(int ordinal) {\n");
                builder.append("        return typeAPI.get").append(methodFieldName).append("Ordinal(ordinal);\n");
                builder.append("    }\n\n");
                break;
            case STRING:
                builder.append("    public String get").append(methodFieldName).append("(int ordinal) {\n");
                builder.append("        return typeAPI.get").append(methodFieldName).append("(ordinal);\n");
                builder.append("    }\n\n");
                builder.append("    public boolean is").append(methodFieldName).append("Equal(int ordinal, String testValue) {\n");
                builder.append("        return typeAPI.is").append(methodFieldName).append("Equal(ordinal, testValue);\n");
                builder.append("    }\n\n");
                break;
            }
        }

        builder.append("    public ").append(typeAPIClassname(schema.getName())).append(" getTypeAPI() {\n");
        builder.append("        return typeAPI;\n");
        builder.append("    }\n\n");

        builder.append("    @Override\n");
        builder.append("    public HollowObjectSchema getSchema() {\n");
        builder.append("        return typeAPI.getTypeDataAccess().getSchema();\n");
        builder.append("    }\n\n");

        builder.append("    @Override\n");
        builder.append("    public HollowObjectTypeDataAccess getTypeDataAccess() {\n");
        builder.append("        return typeAPI.getTypeDataAccess();\n");
        builder.append("    }\n\n");

        builder.append("}");

        return builder.toString();

    }

}
