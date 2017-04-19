import java.io.File;

class FileLoader {

    protected File directory;
    protected File[] files;
    protected String[] filesName;

    FileLoader(String directory, String regExp) {
        this.directory = new File(directory);
        this.files = this.directory.listFiles((File pathname) -> {
            return pathname.getName().matches(regExp);
        });
        this.filesName = this.directory.list((File dir, String fileName) -> fileName.matches(regExp));
    }

    File at(int index) {
        return (index < files.length) ? files[index] : null;
    }

    String nameAt(int index) {
        return (index < filesName.length) ? filesName[index] : null;
    }
}

