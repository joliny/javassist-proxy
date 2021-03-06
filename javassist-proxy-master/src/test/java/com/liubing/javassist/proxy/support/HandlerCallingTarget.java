/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2010, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package com.liubing.javassist.proxy.support;

import java.lang.reflect.Method;

import com.liubing.javassist.proxy.ProxyHandler;

/**
 * 
 * @author <a href="kabir.khan@jboss.com">Kabir Khan</a>
 * @version $Revision: 1.1 $
 */
public class HandlerCallingTarget<T> extends ProxyHandler<T>{

    public Method m;
    
    public Object[] replacementArgs;
    
    public HandlerCallingTarget(T instance, Object[] replacementArgs) {
        super(instance);
        this.replacementArgs = replacementArgs;
    }
    
    @Override
    protected boolean finalCallInHandler(Method m) {
        return true;
    }

    @Override
    protected Object invokeMethod(T instance, Method m, Object[] args) {
        this.m = m;
        
        try {
            return m.invoke(instance, replacementArgs);
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
