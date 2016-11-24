package ideall.gabrielrunescape.com.br.objects;

/**
 *      Criado por GabrielRuneScape <gabrielfilipe@mail.ru> em 23/11/2016
 *
 *      Objeto para armazenamento dos projetos antes de transmitir ao banco de dados através da
 * classe CustomSQLiteOpenHelper.
 */
public class Project {
    private long ID;
    private String name;
    private String author;

    /**
     *      Sobreescreve o método `toString`;
     *
     * @return O JSON do objeto Project
     */
    @Override
    public String toString() {
        return "Project: { ID: " + ID + ", Name: \'" + name + "\', Author: \'" + author + "\'}";
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
