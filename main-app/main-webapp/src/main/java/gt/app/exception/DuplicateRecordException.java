package gt.app.exception;

import gt.common.config.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DuplicateRecordException extends BaseException {

    public DuplicateRecordException(String description) {
        super(description);
    }

    public DuplicateRecordException(String requestedObjectName, String requestedByField, Object requestedByParam) {
        super(String.format("%s with %s = '%s' already exists", requestedObjectName, requestedByField, requestedByParam));
    }
}
