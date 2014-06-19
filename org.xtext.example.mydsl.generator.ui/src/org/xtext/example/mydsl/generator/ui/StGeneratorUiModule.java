package org.xtext.example.mydsl.generator.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.service.AbstractGenericModule;

public class StGeneratorUiModule extends AbstractGenericModule{
	@SuppressWarnings("unused")
	private AbstractUIPlugin plugin;
	
	public StGeneratorUiModule (AbstractUIPlugin plugin){
		this.plugin=plugin;
	}
    /**
     * Bind the JavaProjectBasedBuilderParticipant in order to invoke the generator during the build.
     */
//    @SuppressWarnings("deprecation")
//	public Class<? extends org.eclipse.xtext.builder.IXtextBuilderParticipant> bindIXtextBuilderParticipant() {
//        return org.eclipse.xtext.builder.JavaProjectBasedBuilderParticipant.class;
//    }
    public org.eclipse.core.resources.IWorkspaceRoot bindIWorkspaceRootToInstance() {
        return org.eclipse.core.resources.ResourcesPlugin.getWorkspace().getRoot();
    }
}
