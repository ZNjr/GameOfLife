class Cell {
    enum State {Life, Dead}

    State is;
    State willBe;

    Cell() {
        this.is = State.Dead;
    }
    Cell(int is) {
        this.is = (is == 0) ? State.Dead : State.Life;
    }
}