package com.dmens.pokeno.UtilsTest;
import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.dmens.pokeno.utils.FileUtils;

@RunWith(PowerMockRunner.class)
@PrepareForTest(FileUtils.class)
@PowerMockIgnore("javax.management.*")
public class FileUtilsTest {
	
	private static final String DECKS_LOCATION = "decks";
	static File createdFolder;
	
	@Rule
	public TemporaryFolder folder= new TemporaryFolder();
	
	@Before
	public void setup() throws IOException{	
		PowerMockito.spy(FileUtils.class);
		Mockito.when(FileUtils.getResourcesLocation()).thenReturn(folder.getRoot().toPath().toUri().toString());
		
	    createdFolder = folder.newFolder(DECKS_LOCATION);
	    
	    folder.newFile(createdFolder.getName()+"/deck1.txt");
		folder.newFile(createdFolder.getName()+"/deck2.txt");
		
		File f = folder.newFile("cards.txt");
		
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(URI.create(folder.getRoot().toURI().toString()+"cards.txt")))) {
		    writer.write("Macho\nPikachu");
		}
	}
	
	
	@Test
	public void testGetListOfDeckFiles()  throws Exception{
		List<String> decks = FileUtils.getFilesFromFolder(DECKS_LOCATION, ".txt");
		assertTrue("Expected deck size to be 2 got "+ decks.size(), decks.size() == 2);
	}
	
	@Test
	public void testGetFileContentsAsList() throws IOException{
		List<String> contents = FileUtils.getFileContentsAsList("cards.txt");
		assertTrue("Expected to have 2 cards in list: got "+contents.size(), contents.size() == 2);
		assertTrue("Expected First entry to be Macho, got "+contents.get(0), contents.get(0).equals("Macho"));
	}

}
