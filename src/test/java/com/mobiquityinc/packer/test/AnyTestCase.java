package com.mobiquityinc.packer.test;

import com.mobiquityinc.packer.InputReader;
import com.mobiquityinc.packer.exception.APIException;
import com.mobiquityinc.packer.model.Package;

import java.util.ArrayList;
import java.util.Collection;

class AnyTestcase {

    /**
     * This implementation of {@link InputReader} is only used locally in testcases. It helps simulate a file with
     * a single row, so that each testcase can pass the row information directly instead of storing it in a real file.
     */
    private InputReader rowReader = new InputReader() {
        @Override
        public Collection<Package> readFile(String _filename) throws APIException {
            Collection<Package> singleScenario = new ArrayList<>();
            singleScenario.add(this.parseLine(_filename, 1));
            return singleScenario;
        }
    };

    public InputReader  getRowReader() {
        return rowReader;
    }
}
