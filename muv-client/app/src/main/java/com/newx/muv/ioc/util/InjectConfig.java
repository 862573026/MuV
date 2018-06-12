package com.newx.muv.ioc.util;


/**
 * Created by xuzhijian on 2018/5/7 0007.
 */

public class InjectConfig {

    public boolean clientInject;

    private InjectConfig(Builder builder){
        this.clientInject = builder.clientInject;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder{
        private boolean clientInject = false;

        private Builder() {
        }

        public Builder needClientInject(boolean clientInject){
            this.clientInject = clientInject;
            return this;
        }

        public InjectConfig build() {
            return new InjectConfig(this);
        }

    }
}
