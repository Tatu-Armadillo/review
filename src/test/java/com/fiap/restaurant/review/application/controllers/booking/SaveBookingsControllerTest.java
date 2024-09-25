package com.fiap.restaurant.review.application.controllers.booking;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(SaveBookingsController.class)
class SaveBookingsControllerTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private BookingRepositoy bookingRepositoy;
//
//    @MockBean
//    private TableRepository tableRepository;
//
//    @MockBean
//    private UserRepository userRepository;
//
//    private ObjectMapper objectMapper;
//
//    @BeforeEach
//    void setUp() {
//        objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//    }
//
//    @Test
//    void save() throws Exception {
//        when(tableRepository.findById(1L)).thenReturn(Optional.of(TableModelTestData.createTable()));
//        when(userRepository.findById(1L)).thenReturn(Optional.of(UserModelTestData.createUser()));
//
//        when(bookingRepositoy.save(any(BookingModel.class))).thenReturn(BookingModelTestData.createBooking());
//
//        mockMvc.perform(post("/booking/save")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(BookingModelTestData.createBooking())))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.success").value(true));
//    }
}