# JPA N+1 테스트 및 해결

JPA의 N+1 문제는 연관된 엔티티를 조회할 때 다수의 추가 쿼리가 발생하여 성능이 저하되는 현상입니다. FetchType.EAGER와 FetchType.LAZY 설정에서 N+1 문제가 어떻게 나타나는지 실제로 테스트해보고, BatchSize를 조절하여 추가 쿼리를 최소화하는 방법까지 테스트해보는 레포지토리입니다.
# 관련 블로그 포스팅

해당 레포지토리에 대한 설명을 블로그 글에 작성해놨습니다.

[![블로그 포스팅](https://img.shields.io/badge/블로그-포스팅으로%20이동-orange?logo=tistory)](https://lass9436.tistory.com/5)
