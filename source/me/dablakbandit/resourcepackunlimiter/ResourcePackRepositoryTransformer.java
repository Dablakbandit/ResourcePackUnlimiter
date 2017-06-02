package me.dablakbandit.resourcepackunlimiter;

import static org.objectweb.asm.Opcodes.LDC;

import java.util.Arrays;
import java.util.List;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodNode;

import net.minecraft.launchwrapper.IClassTransformer;

public class ResourcePackRepositoryTransformer implements IClassTransformer{
	
	private static final List<String> classesBeingTransformed = Arrays.asList(new String[]{ "net.minecraft.client.resources.ResourcePackRepository" });
	
	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass){
		boolean isObfuscated = !name.equals(transformedName);
		if(classesBeingTransformed.contains(transformedName)){ return transform(transformedName, basicClass, isObfuscated); }
		return basicClass;
	}
	
	private static byte[] transform(String name, byte[] classBeingTransformed, boolean isObfuscated){
		System.out.println("Transforming: " + name);
		try{
			ClassNode classNode = new ClassNode();
			ClassReader classReader = new ClassReader(classBeingTransformed);
			classReader.accept(classNode, 0);
			
			transform(classNode, isObfuscated);
			
			ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
			classNode.accept(classWriter);
			return classWriter.toByteArray();
		}catch(Exception e){
			e.printStackTrace();
		}
		return classBeingTransformed;
	}
	
	private static void transform(ClassNode classNode, boolean isObfuscated){
		
		final String download = isObfuscated ? "a" : "downloadResourcePack";
		final String desc = "(Ljava/lang/String;Ljava/lang/String;)Lcom/google/common/util/concurrent/ListenableFuture;";
		for(MethodNode method : classNode.methods){
			if(method.name.equals(download) && method.desc.equals(desc)){
				
				for(AbstractInsnNode instruction : method.instructions.toArray()){
					if(instruction.getOpcode() == LDC){
						LdcInsnNode ldc = ((LdcInsnNode)instruction);
						if(ldc.cst.getClass().equals(Integer.class) && (Integer)ldc.cst == 52428800){
							System.out.println("[ResourcePackUnlimiter] Found.");
							ldc.cst = Integer.MAX_VALUE;
						}
					}
				}
				break;
			}
		}
		
	}
	
}
