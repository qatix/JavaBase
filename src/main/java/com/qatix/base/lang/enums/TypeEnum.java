package com.qatix.base.lang.enums;

public enum TypeEnum {
    FIREWALL("firewall"),
    SECRET("secretMac"),
    BALANCE("f5");

    private String typeName;
    TypeEnum(String typeName){
        this.typeName = typeName;
    }

    public static TypeEnum fromName(String name){
        for (TypeEnum typeEnum : TypeEnum.values()){
            if(typeEnum.typeName == name){
                return typeEnum;
            }
        }
        return null;
    }

    public String getTypeName(){
        return this.typeName;
    }
}
