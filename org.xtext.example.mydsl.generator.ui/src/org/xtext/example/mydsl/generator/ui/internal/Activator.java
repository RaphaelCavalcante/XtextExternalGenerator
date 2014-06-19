package org.xtext.example.mydsl.generator.ui.internal;

import java.util.Collections;
import java.util.Map;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.ui.shared.SharedStateModule;
import org.eclipse.xtext.util.Modules2;
import org.osgi.framework.BundleContext;
import org.xtext.example.mydsl.MyDslRuntimeModule;
import org.xtext.example.mydsl.generator.StGeneratorModule;
import org.xtext.example.mydsl.generator.ui.StGeneratorUiModule;

import com.google.common.collect.Maps;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class Activator extends AbstractUIPlugin{
	public static final String ORG_XTEXT_EXAMPLE_MYDSL_MYDSL = "org.xtext.example.mydsl.MyDsl";
	@SuppressWarnings("unused")
	private Injector injector;
	private static Activator INSTANCE;
	private Map<String, Injector> injectors = Collections.synchronizedMap(Maps.<String, Injector> newHashMapWithExpectedSize(1));

	@Override
	public void start(BundleContext context) throws Exception{
		super.start(context);
		INSTANCE=this;
	}
	public Injector getInjector(String language) {
		synchronized (injectors) {
			Injector injector = injectors.get(language);
			if (injector == null) {
				injectors.put(language, injector = createInjector(language));
			}
			return injector;
		}
	}
	protected Injector createInjector(String language){
		//Configura quais os modulos necessários para a execução do plugin.
		Module mergedModule= Modules2.mixin(new MyDslRuntimeModule(), new SharedStateModule(), new StGeneratorModule(), new StGeneratorUiModule(this));
		return Guice.createInjector(mergedModule);
	}

	@Override
	public void stop(BundleContext context) throws Exception{
		injector=null;
		INSTANCE=null;
		super.stop(context);
	}
	
	public static Activator getInstance(){
		return INSTANCE;
	}
	protected Module getRuntimeModule(String grammar) {
		if (ORG_XTEXT_EXAMPLE_MYDSL_MYDSL.equals(grammar)) {
			return new org.xtext.example.mydsl.MyDslRuntimeModule();
		}
		
		throw new IllegalArgumentException(grammar);
	}
}
