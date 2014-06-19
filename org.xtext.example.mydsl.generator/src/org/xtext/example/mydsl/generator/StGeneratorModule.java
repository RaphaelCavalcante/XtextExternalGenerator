package org.xtext.example.mydsl.generator;

import org.eclipse.xtext.service.AbstractGenericModule;

public class StGeneratorModule extends AbstractGenericModule{
	
	public Class <? extends org.eclipse.xtext.generator.IGenerator> bindIGenerator(){
		return MyDslGenerator.class;
	}
	
}
