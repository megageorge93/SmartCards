# SmartCards
26.09 The error appears when: 
Open Emulator => Create or click on Category 
=> create or click on Folder => Create card (it doesn't appear in UI) 
=> Go back by pressing backbutton in ToolBar
Assumption: it might be linked to: 
FolderId is null in ViewModel onTake method. 
OR might be linked with ROOM Database Foreign KEY 
OR Using getAllFolders instead of getAllFoldersFromCategory
