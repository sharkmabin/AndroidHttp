package com.infrastructure.net;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

/**
 * Created by 马彬彬 on 2016/6/25.
 */
public class RequestParameter  implements Serializable,Comparator<Object>{

    private String name ;
    private String value ;

    public RequestParameter(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public boolean equals(final Object o){
        if (null == o){
            return  false;
        }
        if (this == o){
            return true ;
        }
        if (o instanceof RequestParameter){
            final RequestParameter parameter = (RequestParameter) o;
            return name.equals(parameter.name) && value.equals(parameter.value) ;
        }
        return false ;
    }

    @Override
    public int compare(Object lhs, Object rhs) {
        int compared ;
        final RequestParameter parameter = (RequestParameter) rhs;
        compared = name.compareTo(parameter.value) ;
        if (compared == 0){
            compared = value.compareTo(parameter.value) ;
        }
        return compared;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
