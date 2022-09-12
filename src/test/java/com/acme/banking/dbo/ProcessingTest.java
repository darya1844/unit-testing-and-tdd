package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.service.Processing;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ProcessingTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    Cash cash;

    @InjectMocks
    Processing sut;

    @Test
    public void shouldCallCashMethod() {
        sut.cash(1.0, 1);

        verify(cash, times(1)).log(1.0, 1);
    }
}
