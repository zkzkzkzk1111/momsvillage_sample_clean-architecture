package com.ez.domain.usecase

import com.ez.domain.model.EditorMain
import com.ez.domain.repository.EditorMainRepository
import com.ez.domain.util.ApiResult
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetEditorMainUseCaseTest {

    private lateinit var mockRepository: EditorMainRepository // mockRepository 정의 EditorMainRepository로 정의
    private lateinit var getEditorMainUseCase: GetEditorMainUseCase // 테스트할 use case getEditorMainUseCase 정의

    @Before
    fun setup() {
        mockRepository = mockk()     //mockk()로 mockRepository 초기화
        getEditorMainUseCase = GetEditorMainUseCase(mockRepository) //getEditorMainUseCase에 mockRepository 주입
    }

    @Test
    fun `리포지토리가 성공을 반환할 때 EditorMain 목록을 반환해야 합니다`() {
        runTest {
            // given
            val fakeEditorMain = EditorMain(
                postId = 1,
                userId = 100,
                postTitle = "테스트 제목",
                postContent = "테스트 내용",
                postCreateTime = "2025-09-04T10:00:00",
                postUpdateTime = "2025-09-04T10:30:00",
                categoryId = 10,
                categoryName = "테스트 카테고리",
                postImageurl = "https://example.com/image.png",
                userNickname = "테스트유저",
                userProfileImage = "https://example.com/profile.png",
                userStatusMessage = "안녕하세요",
                editorRankId = 1,
                editorRank = "에디터",
                editorRankImage = "https://example.com/rank.png"
            )
            val fakeList = listOf(fakeEditorMain) // 단일 항목을 포함하는 목록 생성 fakeList

            every { mockRepository.getEditorMainBoard() } returns flowOf(ApiResult.Success(fakeList))
            // mockRepository.getEditorMainBoard() 호출 시, ApiResult.Success(fakeList)를 Flow로 반환하도록 설정

            // when
            val result = getEditorMainUseCase().first() // use case 실행 후 Flow에서 첫 번째(유일한) 결과를 가져오기

            // then
            assertTrue(result is ApiResult.Success) // 결과가 ApiResult.Success인지 확인
            val data = (result as ApiResult.Success).data // 성공 결과에서 데이터 추출
            assertEquals(1, data.size) // 데이터 크기가 1인지 확인
            assertEquals("테스트 제목", data[0].postTitle) // 데이터의 각 필드가 예상 값과 일치하는지 확인
            assertEquals("테스트유저", data[0].userNickname) // 닉네임 테스트유저인지 확인
            assertEquals(1, data[0].postId) // postId 1인지 확인
            assertEquals(100, data[0].userId) // userId 100인지 확인

            verify { mockRepository.getEditorMainBoard() } // mockRepository.getEditorMainBoard()가 실제로 호출되었는지 검증
        }
    }

    @Test
    fun `리포지토리가 빈 데이터로 성공을 반환할 때 호출은 빈 목록을 반환해야 합니다`() {
        runTest {
            // given
            val emptyList = emptyList<EditorMain>()
            every { mockRepository.getEditorMainBoard() } returns flowOf(ApiResult.Success(emptyList))

            // when
            val result = getEditorMainUseCase().first()

            // then
            assertTrue(result is ApiResult.Success)
            val data = (result as ApiResult.Success).data
            assertTrue(data.isEmpty())

            verify { mockRepository.getEditorMainBoard() }
        }
    }

    @Test
    fun `여러개_아이템_조회시_올바르게_처리되어야한다`() {
        runTest {
            // given
            val fakeList = listOf(
                EditorMain(
                    postId = 1,
                    userId = 100,
                    postTitle = "첫번째 제목",
                    postContent = "첫번째 내용",
                    postCreateTime = "2025-09-04T10:00:00",
                    postUpdateTime = "2025-09-04T10:30:00",
                    categoryId = 10,
                    categoryName = "카테고리1",
                    postImageurl = "https://example.com/image1.png",
                    userNickname = "유저1",
                    userProfileImage = "https://example.com/profile1.png",
                    userStatusMessage = "안녕하세요1",
                    editorRankId = 1,
                    editorRank = "에디터",
                    editorRankImage = "https://example.com/rank1.png"
                ),
                EditorMain(
                    postId = 2,
                    userId = 200,
                    postTitle = "두번째 제목",
                    postContent = "두번째 내용",
                    postCreateTime = "2025-09-04T11:00:00",
                    postUpdateTime = "2025-09-04T11:30:00",
                    categoryId = 20,
                    categoryName = "카테고리2",
                    postImageurl = "https://example.com/image2.png",
                    userNickname = "유저2",
                    userProfileImage = "https://example.com/profile2.png",
                    userStatusMessage = "안녕하세요2",
                    editorRankId = 2,
                    editorRank = "시니어에디터",
                    editorRankImage = "https://example.com/rank2.png"
                )
            )
            every { mockRepository.getEditorMainBoard() } returns flowOf(ApiResult.Success(fakeList))

            // when
            val result = getEditorMainUseCase().first()

            // then
            assertTrue(result is ApiResult.Success)
            val data = (result as ApiResult.Success).data
            assertEquals(2, data.size)
            assertEquals("첫번째 제목", data[0].postTitle)
            assertEquals("두번째 제목", data[1].postTitle)
            assertEquals("유저1", data[0].userNickname)
            assertEquals("유저2", data[1].userNickname)

            verify { mockRepository.getEditorMainBoard() }
        }
    }

    @After
    fun tearDown() {
        unmockkAll() // 모든 Mock 해제
    }
}