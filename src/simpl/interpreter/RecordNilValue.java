package simpl.interpreter;

class RecordNilValue extends Value {

	protected RecordNilValue() {
	}

	public String toString() {
		return "recordnil";
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof RecordNilValue) {
			return true;
		}
		return false;
	}

}
