package sudoku;

public class ImmutableCell implements Cell{

	private int index;
    private int value;

    public ImmutableCell(int index, int value) {
        this.index = index;
        this.value = value;
    }

    @Override
    public Integer getIndex() {
        return index;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
