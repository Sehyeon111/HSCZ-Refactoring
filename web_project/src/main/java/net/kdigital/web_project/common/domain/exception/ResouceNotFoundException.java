package net.kdigital.web_project.common.domain.exception;

public class ResouceNotFoundException extends RuntimeException {

    public ResouceNotFoundException(String dataSource, String id) {
        super(dataSource + "에서 " + id + "를 찾을 수 없습니다!");
    }
}
