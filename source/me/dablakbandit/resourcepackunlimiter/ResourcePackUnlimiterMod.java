package me.dablakbandit.resourcepackunlimiter;

import java.util.Map;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.MCVersion("1.11.2")
@IFMLLoadingPlugin.TransformerExclusions("me.dablakbandit.resourcepackunlimiter")
public class ResourcePackUnlimiterMod implements IFMLLoadingPlugin{
	
	@Override
	public String[] getASMTransformerClass(){
		return new String[]{ "me.dablakbandit.resourcepackunlimiter.ResourcePackRepositoryTransformer" };
	}
	
	@Override
	public String getModContainerClass(){
		return "me.dablakbandit.resourcepackunlimiter.ResourcePackUnlimiterContainer";
	}
	
	@Override
	public String getSetupClass(){
		return null;
	}
	
	@Override
	public void injectData(Map<String, Object> data){
	}
	
	@Override
	public String getAccessTransformerClass(){
		return null;
	}
	
}
