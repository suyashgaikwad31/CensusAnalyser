package censusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try ( Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            CsvToBeanBuilder<IndiaCensusCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(IndiaCensusCSV.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<IndiaCensusCSV> csvToBean = csvToBeanBuilder.build();
            Iterator<IndiaCensusCSV> censusCSVIterator = csvToBean.iterator();;
            Iterable<IndiaCensusCSV> csvIterable = () -> censusCSVIterator;
            int num0Enteries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
            return num0Enteries;
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                                                CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (IllegalStateException e) {
            throw new CensusAnalyserException(e.getMessage(),
                                                CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }

    }

    public int loadIndianStateCode(String csvFilePath) throws CensusAnalyserException {
        try ( Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            CsvToBeanBuilder<IndiaStateCodeCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(IndiaStateCodeCSV.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<IndiaStateCodeCSV> csvToBean = csvToBeanBuilder.build();
            Iterator<IndiaStateCodeCSV> stateCSVIterator = csvToBean.iterator();;
            Iterable<IndiaStateCodeCSV> csvIterable = () -> stateCSVIterator;
            int num0Enteries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
            return num0Enteries;
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                                                CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (IllegalStateException e) {
            throw new CensusAnalyserException(e.getMessage(),
                                                CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }

    }
}
