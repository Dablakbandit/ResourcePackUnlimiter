package me.dablakbandit.resourcepackunlimiter;

import java.util.Arrays;

import com.google.common.eventbus.EventBus;

import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;

public class ResourcePackUnlimiterContainer extends DummyModContainer{
	
	public ResourcePackUnlimiterContainer(){
		super(new ModMetadata());
		ModMetadata meta = getMetadata();
		meta.modId = "resourcepackunlimiter";
		meta.name = "ResourcePack Unlimiter";
		meta.description = "No more limit on resourcepacks.";
		meta.version = "v1.11-1.0.0";
		meta.authorList = Arrays.asList(new String[]{ "Dablakbandit" });
	}
	
	@Override
	public boolean registerBus(EventBus bus, LoadController controller){
		bus.register(this);
		return true;
	}
}
